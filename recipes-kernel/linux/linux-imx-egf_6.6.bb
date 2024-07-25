# Copyright (C) 2013-2016 Freescale Semiconductor
# Copyright 2017 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Linux kernel provided and supported by EGF"
DESCRIPTION = "Linux kernel provided and supported by EGF (based on the kernel provided by NXP) \
with focus on i.MX Family SOMs. It includes support for many IPs such as GPU, VPU and IPU."

require recipes-kernel/linux/linux-imx.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

FILES:${KERNEL_PACKAGE_NAME}-base += "${nonarch_base_libdir}/modules/${KERNEL_VERSION}/modules.builtin.modinfo "

DEPENDS += "lzop-native bc-native"

DEFAULT_PREFERENCE = "1"

SRCBRANCH = "imx-6.6.23-2.0.0_egf"
KERNEL_SRC ?= "git://gitrepo.egf.it/imx53/kernel;protocol=http"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"
SRCREV = "963b078c88cc3b205543cd3c16dafa6031f3de74"
LINUX_VERSION = "6.6"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

KBUILD_DEFCONFIG:mx8-nxp-bsp = "imx_v8_egf_defconfig"

pkg_postinst:kernel-devicetree:append () {
   rm -f $D/boot/devicetree-*
}


KERNEL_VERSION_SANITY_SKIP="1"
COMPATIBLE_MACHINE = "(mx6|mx7|mx8)"
