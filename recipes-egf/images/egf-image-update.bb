LICENSE = "MIT"

GF_YOCTO_ROOTFS_LIVE_VERSION = "1.0"

IMAGE_LINGUAS = ""

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image

IMAGE_ROOTFS_SIZE = "8192"

IMAGE_INSTALL:remove = " packagegroup-fsl-bluez5-tools"
IMAGE_INSTALL:remove = " packagegroup-fsl-tools-gpu"

PACKAGE_EXCLUDE = "kernel-*"

PACKAGE_INSTALL = "initramfs-boot busybox udev base-passwd udev-extraconf \
				  dosfstools e2fsprogs openssl-bin tar bzip2 zstd \
				  unzip nano util-linux mmc-utils \
				  iproute2 "

write_version () {
    #write file version on filesystem
	echo ${GF_YOCTO_ROOTFS_LIVE_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf
}


IMAGE_PREPROCESS_COMMAND += "write_version; "


IMAGE_VERSION_SUFFIX = "-${GF_YOCTO_ROOTFS_LIVE_VERSION}"
IMAGE_BASENAME = "egf-image-update"

