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
IMAGE_INSTALL += " chromium-ozone-wayland "



