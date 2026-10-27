DESCRIPTION = "i.MX U-Boot supporting i.MX EGF boards."

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

UBOOT_SRC ?= "git://github.com/elettronicagf/uboot-imx-egf;protocol=https"

SRCBRANCH = "imx-6.18.37-2.1.0_egf"
SRCREV = "1a658558a66d4bd8f41d6dd724cc34555bfa2b12"

SRCBRANCH:imx8mp-egf-3sm2008 = "dev-3sm2008"
SRCREV:imx8mp-egf-3sm2008 = "df9038d74d1cd579381f20dfba21a9a0de9802d3"

SRCBRANCH:imx8mm-egf-3sm2010 = "dev-3sm2010"
SRCREV:imx8mm-egf-3sm2010 = "4682edb0619798d61db0a976b31cea25ca6c3f10"


SRC_URI = "${UBOOT_SRC};branch=${SRCBRANCH}"

BOOT_TOOLS = "imx-boot-tools"

DEPENDS += "\
    bc-native \
    bison-native \
    dtc-native \
    flex-native \
    gnutls-native \
    python3-setuptools-native \
    xxd-native \
    efitools-native \
"

B = "${WORKDIR}/build"

