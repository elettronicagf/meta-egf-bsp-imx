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
SRCREV = "7cb309779eedbe7f23a069aa8f6e68c9ff6031e3"

SRCBRANCH:imx93-egf-3sm1010 = "imx-6.6.23-2.0.0_egf_3sm1010-dev"
SRCREV:imx93-egf-3sm1010 = "e5f457bfb8c3db5257b3d44b039e6e9bfbd82d2f"

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


PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"