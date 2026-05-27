require dynamic-layers/qt6-layer/recipes-fsl/images/imx-image-full.bb

### falcon mode (conditional usage inside .inc)
include falcon.inc



#versioning
GF_YOCTO_ROOTFS_VERSION = "0.8"
IMAGE_VERSION_SUFFIX = "-${GF_YOCTO_ROOTFS_VERSION}"
IMAGE_BASENAME = "egf-image"
write_version () {
	echo ${GF_YOCTO_ROOTFS_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf
}
IMAGE_PREPROCESS_COMMAND += "write_version;"


#package lists
#EGF board supports RS485. libmodbus is useful.
IMAGE_INSTALL:append = "libmodbus "
#for html applications (only for mx8 cpu)
IMAGE_INSTALL:append:mx8-generic-bsp = "chromium-ozone-wayland "

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
 

IMAGE_ROOTFS_SIZE = "4000000"

CORE_IMAGE_EXTRA_INSTALL:remove = "packagegroup-fsl-tools-benchmark"
CORE_IMAGE_EXTRA_INSTALL:remove = "packagegroup-fsl-tools-gpu"
