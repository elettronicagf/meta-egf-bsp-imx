require recipes-fsl/images/imx-image-multimedia.bb

### falcon mode (conditional usage inside .inc)
include falcon.inc

### Initialize distro version from manifest tag
inherit egf_distro_version

### Initialize buildinfo
inherit image-buildinfo
IMAGE_BUILDINFO_VARS += "IMAGE_BASENAME DATETIME EGF_DISTRO_VERSION"


#package lists
#EGF board supports RS485. libmodbus is useful.
IMAGE_INSTALL:append = "libmodbus "

IMAGE_INSTALL:append = "iperf3 "
IMAGE_INSTALL:append = "hdparm "
IMAGE_INSTALL:append = "python3-pyserial "

IMAGE_INSTALL:append = "${@bb.utils.contains('MACHINE_FEATURES', 'multistd-serial', 'serial-multistd-config ', '' , d)}"
IMAGE_INSTALL:append = "${@bb.utils.contains('MACHINE_FEATURES', 'egf-init-service', 'egf-init-service ', '' , d)}"

IMAGE_INSTALL:remove = " connman"
IMAGE_INSTALL:remove = " connman-client"
IMAGE_INSTALL:remove = " connman-gnome"
IMAGE_INSTALL:remove = " connman-plugin-wifi"
IMAGE_INSTALL:remove = " connman-plugin-ethernet"
IMAGE_INSTALL:remove = " connman-plugin-loopback"
 

CORE_IMAGE_EXTRA_INSTALL:remove = "packagegroup-fsl-tools-benchmark"
CORE_IMAGE_EXTRA_INSTALL:remove = "packagegroup-fsl-tools-gpu"

IMAGE_ROOTFS_SIZE =   "2500000"