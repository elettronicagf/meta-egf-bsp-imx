FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:imx8mp-egf-3sm1009 = "git://bitbucket.org/egf-common/imx-atf-egf.git;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH:imx8mp-egf-3sm1009 = "imx-6.6.23-2.0.0_egf"
SRCREV:imx8mp-egf-3sm1009 = "a7631744156697bb813af3e96544b86c3942f38e"

SRC_URI:imx8mm-egf-3sm1008m = "git://bitbucket.org/egf-common/imx-atf-egf.git;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH:imx8mm-egf-3sm1008m = "imx-6.6.23-2.0.0_egf"
SRCREV:imx8mm-egf-3sm1008m = "a7631744156697bb813af3e96544b86c3942f38e"