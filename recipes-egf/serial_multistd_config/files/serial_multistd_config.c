// SPDX-License-Identifier: GPL-2.0-or-later
// derived from https://github.com/brgl/libgpiod/raw/master/examples/toggle_line_value.c
// derived from https://gist.github.com/amarburg/07564916d8d32e20e6ae375c1c83a995
// Tool to setup multiserial IC on EGF SOM

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <fcntl.h>
#include <linux/serial.h>
#include <sys/ioctl.h>


#ifndef DEBUG_ON_PC
#include <gpiod.h>

#ifndef CONSUMER
#define CONSUMER "Consumer"
#endif
struct gpiod_chip *chip2;
struct gpiod_chip *chip4;
struct gpiod_line *line_n_shdn;
struct gpiod_line *line_echo_enable;
struct gpiod_line *line_slew_rate;
struct gpiod_line *line_rs485_enable;
struct gpiod_line *line_half_duplex_enable;

static struct gpiod_line_request *request_output_line(const char *chip_path, unsigned int offset,
													  enum gpiod_line_value value, const char *consumer)
{
	struct gpiod_request_config *req_cfg = NULL;
	struct gpiod_line_request *request = NULL;
	struct gpiod_line_settings *settings;
	struct gpiod_line_config *line_cfg;
	struct gpiod_chip *chip;
	int ret;

	chip = gpiod_chip_open(chip_path);
	if (!chip)
		return NULL;

	settings = gpiod_line_settings_new();
	if (!settings)
		goto close_chip;

	gpiod_line_settings_set_direction(settings,
									  GPIOD_LINE_DIRECTION_OUTPUT);
	gpiod_line_settings_set_output_value(settings, value);

	line_cfg = gpiod_line_config_new();
	if (!line_cfg)
		goto free_settings;

	ret = gpiod_line_config_add_line_settings(line_cfg, &offset, 1,
											  settings);
	if (ret)
		goto free_line_config;

	if (consumer)
	{
		req_cfg = gpiod_request_config_new();
		if (!req_cfg)
			goto free_line_config;

		gpiod_request_config_set_consumer(req_cfg, consumer);
	}

	request = gpiod_chip_request_lines(chip, req_cfg, line_cfg);

	gpiod_request_config_free(req_cfg);

free_line_config:
	gpiod_line_config_free(line_cfg);

free_settings:
	gpiod_line_settings_free(settings);

close_chip:
	gpiod_chip_close(chip);

	return request;
}

void enable_rs485(const char *port, int enable)
{
	int fd = open(port, O_RDWR);

	if (fd < 0)
	{
		/* Error handling. See errno. */
		fprintf(stderr, "Error opening port \"%s\" (%d): %s\n", port, errno, strerror(errno));
		exit(-1);
	}

	struct serial_rs485 rs485conf;

	if (ioctl(fd, TIOCGRS485, &rs485conf) < 0)
	{
		fprintf(stderr, "Error reading ioctl port (%d): %s\n", errno, strerror(errno));
	}

	printf("Port currently RS485 mode is %s\n", (rs485conf.flags & SER_RS485_ENABLED) ? "set" : "NOT set");

	if (enable)
	{

		printf("RS485 mode will be SET with RTS_ON_SEND and RS485_RX_DURING_TX\n");
		rs485conf.flags |= SER_RS485_ENABLED | SER_RS485_RTS_ON_SEND | SER_RS485_RX_DURING_TX;
	}
	else
	{
		printf("RS485 mode will be UNSET\n");
		rs485conf.flags &= ~SER_RS485_ENABLED;
	}

	if (ioctl(fd, TIOCSRS485, &rs485conf) < 0)
	{
		fprintf(stderr, "Error sending ioctl port (%d): %s\n", errno, strerror(errno));
	}

	/* Use read() and write() syscalls here... */

	if (ioctl(fd, TIOCGRS485, &rs485conf) < 0)
	{
		fprintf(stderr, "Error reading ioctl port (%d): %s\n", errno, strerror(errno));
	}

	printf("Confirm RS485 mode is %s\n", (rs485conf.flags & SER_RS485_ENABLED) ? "set" : "NOT set");
}

int set_output(const char *chip_path, int line_offset, int value)
{

	struct gpiod_line_request *request;
	printf("%s %d => %d\n",chip_path, line_offset, value);

	request = request_output_line(chip_path, line_offset, (enum gpiod_line_value)value,
								  "toggle-line-value");
	if (!request)
	{
		fprintf(stderr, "failed to request line: %s\n",
				strerror(errno));
		exit(-1);
	}

	gpiod_line_request_set_value(request, line_offset, (enum gpiod_line_value) value);

	gpiod_line_request_release(request);

	return 0;
}


void set_io(int n_shdn, int echo, int half_duplex, int rs485_en, int slew)
{
#if WSM0880
	set_output("/dev/gpiochip4", 1, echo);
	set_output("/dev/gpiochip2", 20, slew);
	set_output("/dev/gpiochip2", 24, n_shdn);
	set_output("/dev/gpiochip4", 27, rs485_en);
	set_output("/dev/gpiochip4", 26, half_duplex);
	enable_rs485("/dev/ttymxc1",rs485_en);
#elif WSM0890
#define GPIO_EXPANDER_U77	"/dev/gpiochip7"
	set_output(GPIO_EXPANDER_U77, 7, echo); 		// P0_7
	set_output(GPIO_EXPANDER_U77, 15, slew);		// P1_7
	set_output(GPIO_EXPANDER_U77, 14, n_shdn);		// P1_6
	set_output(GPIO_EXPANDER_U77, 13, rs485_en);    // P1_5
	set_output(GPIO_EXPANDER_U77, 12, half_duplex); // P1_4
	enable_rs485("/dev/ttymxc0",rs485_en);
#elif WSM0890_0820
#define GPIO_EXPANDER_U42	"/dev/gpiochip8"
	set_output(GPIO_EXPANDER_U42, 7, echo); 		// P0_7
	set_output(GPIO_EXPANDER_U42, 15, slew);		// P1_7
	set_output(GPIO_EXPANDER_U42, 14, n_shdn);		// P1_6
	set_output(GPIO_EXPANDER_U42, 13, rs485_en);    // P1_5
	set_output(GPIO_EXPANDER_U42, 12, half_duplex); // P1_4
	enable_rs485("/dev/ttyLP0",rs485_en);#else
#error "undefined Board model"
#endif
}
#else
void set_io(int n_shdn, int echo, int half_duplex, int rs485_en, int slew)
{
}

#endif

void print_usage()
{
#if WSM0880
	printf("serial_multistd_config [-m off/rs232/rs485hd/rs485fd] [-e 0|1] [-s 0|1]\nuart /dev/ttymxc1 on connector CN15\n");
#elif WSM0890
	printf("serial_multistd_config [-m off/rs232/rs485hd/rs485fd] [-e 0|1] [-s 0|1]\nuart /dev/ttymxc0 on connector CN33\n");
#endif
}


int main(int argc, char **argv)
{

	char *par = "off";
	int echo = 0;
	int slew = 0;
	int c;



  while ((c = getopt(argc, argv, "m:e:s:")) != -1)
  {
	
    switch (c)
      {
      case 'e':
        echo = atoi(optarg);
        break;
      case 's':
        slew = atoi(optarg);
        break;
	  case 'm':
	    par = optarg;
      default:
        break;
      }
  }

	if ((echo !=0 && echo!=1) || (slew !=0 && slew!=1) )
	{
		print_usage();
		exit(-1);
	}


	if (!strcmp(par, "off"))
	{
		printf("Switching Off Transceiver\n");
		set_io(0, echo, 0, 0, slew);
	}
	else if (!strcmp(par, "rs232"))
	{
		set_io(1, echo, 1, 0, slew);
	}
	else if (!strcmp(par, "rs485hd"))
	{
		set_io(1, echo, 1, 1, slew);
	}
	else if (!strcmp(par, "rs485fd"))
	{
		set_io(1, echo, 0, 1, slew);
	}
	else
	{
		print_usage();
		return -1;
	}
	return 0;
}