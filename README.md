How to enable falcon mode in Yocto build
----------------------------------------

Simply add

```
DISTRO_FEATURES:append = " falconmode"
BBMULTICONFIG = "mfgtool"
```

in conf/local.conf


How to flash the board with UUU and falcon mode
-----------------------------------------------

When falcon mode is active, we need two different imx-boot files: one with falcon mode inactive
(used with UUU), and the other with falcon mode active. The latter is the one the
taht will be flashed on board, the former is the one that is executed in RAM and that does
the board programming.
When falcon mode is active, the programming imx-boot is automatically built in
tmp-mfgtool/deploy/images/MACHINE and is named 'imx-boot-MACHINE-emmc.bin-flash_evk'.
The imx-boot that will be flashed on board is built in tmp/deploy/images/MACHINE
and is named 'falcon-imx-boot-MACHINE-emmc.bin-flash_evk'. In the same directory
there is thewic.zst image.

The board can be flashed with:

  $ uuu -b emmc_all imx-boot-MACHINE-emmc.bin-flash_evk image-xxxx.wic.zst
  $ uuu -b emmc imx-boot-MACHINE-emmc.bin-flash_evk falcon-imx-boot-MACHINE-emmc.bin-flash_evk

At first boot, stop the boot in u-boot and run:

  => run prepare_fdt
  => reset

Falcon mode is now active.