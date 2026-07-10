require recipes-fsl/images/imx-image-multimedia.bb




#versioning
GF_YOCTO_ROOTFS_CONN_VERSION = "0.0.1"
IMAGE_VERSION_SUFFIX = "-${GF_YOCTO_ROOTFS_CONN_VERSION}"
IMAGE_BASENAME = "egf-image-connectivity"
write_version () {
	echo ${GF_YOCTO_ROOTFS_CONN_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf
}
IMAGE_PREPROCESS_COMMAND += "write_version;"


#package lists

IMAGE_INSTALL:append = "egf-init-service serial-multistd-config "


IMAGE_INSTALL:remove = " connman"
IMAGE_INSTALL:remove = " connman-client"
IMAGE_INSTALL:remove = " connman-gnome"
IMAGE_INSTALL:remove = " connman-plugin-wifi"
IMAGE_INSTALL:remove = " connman-plugin-ethernet"
IMAGE_INSTALL:remove = " connman-plugin-loopback"
IMAGE_INSTALL:append = "libmbim "
IMAGE_INSTALL:append = "libndp "
IMAGE_INSTALL:append = "libqmi "
IMAGE_INSTALL:append = "modemmanager "
IMAGE_INSTALL:append = "networkmanager-adsl "
IMAGE_INSTALL:append = "networkmanager-cloud-setup "
IMAGE_INSTALL:append = "networkmanager-daemon "
IMAGE_INSTALL:append = "networkmanager-nmcli "
IMAGE_INSTALL:append = "networkmanager-wifi "
IMAGE_INSTALL:append = "networkmanager "
IMAGE_INSTALL:append = "nftables "


CORE_IMAGE_EXTRA_INSTALL:remove = "packagegroup-fsl-tools-benchmark"
CORE_IMAGE_EXTRA_INSTALL:remove = "packagegroup-fsl-tools-gpu"

IMAGE_ROOTFS_SIZE =   "5700000"