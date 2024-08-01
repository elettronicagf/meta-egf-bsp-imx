require dynamic-layers/qt6-layer/recipes-fsl/images/imx-image-full.bb




#versioning
GF_YOCTO_ROOTFS_VERSION = "0.1"
IMAGE_VERSION_SUFFIX = "-${GF_YOCTO_ROOTFS_VERSION}"
IMAGE_BASENAME = "egf-image"
write_version () {
	echo ${GF_YOCTO_ROOTFS_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf
}
IMAGE_PREPROCESS_COMMAND += "write_version;"


#package lists
#EGF board supports RS485. libmodbus is useful.
IMAGE_INSTALL:append = "libmodbus "
#for html applications
IMAGE_INSTALL:append = "chromium-ozone-wayland "

IMAGE_INSTALL:append = "egf-init-service "

IMAGE_ROOTFS_SIZE = "4000000"

CORE_IMAGE_EXTRA_INSTALL:remove = "packagegroup-fsl-tools-benchmark"
CORE_IMAGE_EXTRA_INSTALL:remove = "packagegroup-fsl-tools-gpu"
