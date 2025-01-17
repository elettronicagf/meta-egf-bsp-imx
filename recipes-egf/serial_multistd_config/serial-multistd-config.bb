
DESCRIPTION = " tool to setup SP330EEY multiserial IC on EGF Boards"
PV = "1.1"
DEPENDS = " libgpiod "
SRC_URI = "file://serial_multistd_config.c"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-or-later;md5=fed54355545ffd980b814dab4a3b312c"


BOARDCONFIG:imx8mp-egf-3sm1009  = "-DWSM0890=1"
BOARDCONFIG:imx8mm-egf-3sm1008m = "-DWSM0880=1"


do_compile() {
    ${CC} ${BOARDCONFIG}  -Wl,--hash-style=gnu -o  ${WORKDIR}/serial_multistd_config -lgpiod ${WORKDIR}/serial_multistd_config.c
}



do_install() {
    install -d ${D}${sbindir}/
    install -m 0755 ${WORKDIR}/serial_multistd_config ${D}${sbindir}/serial_multistd_config
}

PACKAGE_ARCH = "${MACHINE_ARCH}"