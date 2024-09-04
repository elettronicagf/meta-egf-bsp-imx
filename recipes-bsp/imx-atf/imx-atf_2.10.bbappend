FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:imx8mp-egf-3sm1009 = "git://bitbucket.org/egf-common/imx-atf-egf.git;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH:imx8mp-egf-3sm1009 = "imx-6.6.23-2.0.0_egf"
SRCREV:imx8mp-egf-3sm1009 = "207ba9d98c6d01f65a39aa175eab800d6a0a24e4"

SRC_URI:imx8mm-egf-3sm1008m = "git://bitbucket.org/egf-common/imx-atf-egf.git;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH:imx8mm-egf-3sm1008m = "imx-6.6.23-2.0.0_egf"
SRCREV:imx8mm-egf-3sm1008m = "207ba9d98c6d01f65a39aa175eab800d6a0a24e4"