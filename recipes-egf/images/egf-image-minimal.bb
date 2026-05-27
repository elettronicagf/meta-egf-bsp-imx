require recipes-core/images/core-image-base.bb

### falcon mode (conditional usage inside .inc)
include falcon.inc



#versioning
GF_YOCTO_ROOTFS_VERSION = "0.6"
IMAGE_VERSION_SUFFIX = "-${GF_YOCTO_ROOTFS_VERSION}"
IMAGE_BASENAME = "egf-image-minimal"
write_version () {
	echo ${GF_YOCTO_ROOTFS_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf
}
IMAGE_PREPROCESS_COMMAND += "write_version;"


#package lists
#EGF board supports RS485. libmodbus is useful.
IMAGE_INSTALL:append = "libmodbus "
#for html applications
IMAGE_INSTALL:append = "python3-pyserial "
IMAGE_INSTALL:append = "python3-pip "
IMAGE_INSTALL:append = "nano "
IMAGE_INSTALL:append = "p7zip "
IMAGE_INSTALL:append = "i2c-tools "
IMAGE_INSTALL:append = "egf-init-service serial-multistd-config "

IMAGE_INSTALL:remove = " connman"
IMAGE_INSTALL:remove = " connman-client"
IMAGE_INSTALL:remove = " connman-gnome"
IMAGE_INSTALL:remove = " connman-plugin-wifi"
IMAGE_INSTALL:remove = " connman-plugin-ethernet"
IMAGE_INSTALL:remove = " connman-plugin-loopback"

IMAGE_FEATURES += " \
    debug-tweaks \
    package-management \
    ssh-server-openssh \
"


IMAGE_ROOTFS_SIZE =   "5500000"
