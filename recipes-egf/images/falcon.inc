UBOOT_TARGET_RENAMED = "${UBOOT_TARGET}"
UBOOT_TARGET_RENAMED:mx8m-generic-bsp = "u-boot-atf.itb"

IMAGE_BOOT_FILES:append = "${@bb.utils.contains('DISTRO_FEATURES', 'falconmode', ' \
   ${UBOOT_TARGET};${UBOOT_TARGET_RENAMED} \
   ${KERNEL_TARGET} \
   ', '', d)}"

ROOTFS_POSTPROCESS_COMMAND:append = "${@bb.utils.contains('DISTRO_FEATURES', 'falconmode', ' install_uimage', '', d)}"

install_uimage() {
    mkdir -p ${IMAGE_ROOTFS}/home/root/.falcon
    cp ${DEPLOY_DIR_IMAGE}/uImage ${IMAGE_ROOTFS}/home/root/.falcon
}

do_image[mcdepends] += "${@bb.utils.contains('DISTRO_FEATURES', 'falconmode', 'mc::mfgtool:imx-boot:do_deploy', '', d)}"