require recipes-fsl/images/imx-image-multimedia.bb




#versioning
GF_YOCTO_ROOTFS_TEST_VERSION = "0.7.3"
IMAGE_VERSION_SUFFIX = "-${GF_YOCTO_ROOTFS_TEST_VERSION}"
IMAGE_BASENAME = "egf-image-test"
write_version () {
	echo ${GF_YOCTO_ROOTFS_TEST_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf
}
IMAGE_PREPROCESS_COMMAND += "write_version;"


#package lists
#EGF board supports RS485. libmodbus is useful.
IMAGE_INSTALL:append = "libmodbus "

IMAGE_INSTALL:append = "iperf3 "
IMAGE_INSTALL:append = "hdparm "
IMAGE_INSTALL:append = "python3-pyserial "

IMAGE_INSTALL:append = "egf-init-service serial-multistd-config "

IMAGE_INSTALL:remove = " connman"
IMAGE_INSTALL:remove = " connman-client"
IMAGE_INSTALL:remove = " connman-gnome"
IMAGE_INSTALL:remove = " connman-plugin-wifi"
IMAGE_INSTALL:remove = " connman-plugin-ethernet"
IMAGE_INSTALL:remove = " connman-plugin-loopback"
 

CORE_IMAGE_EXTRA_INSTALL:remove = "packagegroup-fsl-tools-benchmark"
CORE_IMAGE_EXTRA_INSTALL:remove = "packagegroup-fsl-tools-gpu"

IMAGE_ROOTFS_SIZE =   "2500000"