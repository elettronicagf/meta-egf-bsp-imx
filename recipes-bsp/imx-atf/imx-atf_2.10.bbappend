FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:imx8mp-egf-3sm1009 = "git://github.com/elettronicagf/imx-atf-egf;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH:imx8mp-egf-3sm1009 = "imx-6.6.23-2.0.0_egf"
SRCREV:imx8mp-egf-3sm1009 = "db54f9bc3e5dcf5f09a4c9434169244211894586"

SRC_URI:imx8mm-egf-3sm1008m = "git://github.com/elettronicagf/imx-atf-egf;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH:imx8mm-egf-3sm1008m = "imx-6.6.23-2.0.0_egf"
SRCREV:imx8mm-egf-3sm1008m = "db54f9bc3e5dcf5f09a4c9434169244211894586"

SRC_URI:imx93-egf-3sm1010 = "git://github.com/elettronicagf/imx-atf-egf;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH:imx93-egf-3sm1010 = "imx-6.6.23-2.0.0_egf"
SRCREV:imx93-egf-3sm1010 = "db54f9bc3e5dcf5f09a4c9434169244211894586"