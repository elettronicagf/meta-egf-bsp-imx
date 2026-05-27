# Copyright (C) 2013-2016 Freescale Semiconductor
# Copyright 2017-2024 NXP
# Copyright 2024 Elettronica GF s.r.l con Socio Unico

SUMMARY = "U-Boot for eGF Boards"
require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"
DEPENDS += "bison-native bc-native dtc-native gnutls-native"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

UBOOT_SRC ?= "git://github.com/elettronicagf/uboot-imx-egf;protocol=https"

SRCBRANCH = "imx-6.6.23-2.0.0_egf"
SRCREV = "aa7cb8d242ee2632385a0d4640346867f77aa23a"

SRCBRANCH:imx8mp-egf-3sm2008 = "dev-3sm2008"
SRCREV:imx8mp-egf-3sm2008 = "df9038d74d1cd579381f20dfba21a9a0de9802d3"

SRCBRANCH:imx8mm-egf-3sm2010 = "dev-3sm2010"
SRCREV:imx8mm-egf-3sm2010 = "4682edb0619798d61db0a976b31cea25ca6c3f10"


SRC_URI = "${UBOOT_SRC};branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

BOOT_TOOLS = "imx-boot-tools"

do_deploy:append:mx8m-nxp-bsp () {
    # Deploy the mkimage, u-boot-nodtb.bin and the U-Boot dtb for mkimage to generate boot binary
    if [ -n "${UBOOT_CONFIG}" ]
    then
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]
                then
                    install -d ${DEPLOYDIR}/${BOOT_TOOLS}
                    install -m 0777 ${B}/${config}/arch/arm/dts/${UBOOT_DTB_NAME}  ${DEPLOYDIR}/${BOOT_TOOLS}
                    for dtb in ${UBOOT_DTB_EXTRA}; do
                        install -m 0777 ${B}/${config}/arch/arm/dts/${dtb} ${DEPLOYDIR}/${BOOT_TOOLS}
                    done
                    install -m 0777 ${B}/${config}/u-boot-nodtb.bin  ${DEPLOYDIR}/${BOOT_TOOLS}/u-boot-nodtb.bin-${MACHINE}-${UBOOT_CONFIG}
                fi
            done
            unset  j
        done
        unset  i
    fi

}

### falcon mode enablement ###
SRC_URI:append:mx8m-generic-bsp = "${@bb.utils.contains('DISTRO_FEATURES', 'falconmode', ' \
	file://0001-imx8m-reset-ethernet-phy-in-spl.patch \
	file://0001-add-falcon-mode-support.patch \
	file://0002-add-high-speed-pinctrls-in-spl.patch \
	file://0003-Disable-vterm-blinking-cursor.patch \
	file://falcon.cfg \
	', '', d)}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"