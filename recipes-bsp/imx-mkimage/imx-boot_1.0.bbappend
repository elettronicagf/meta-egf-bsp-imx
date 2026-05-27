
### falcon mode enablement ###
DEPENDS:append = "${@bb.utils.contains('DISTRO_FEATURES', 'falconmode', ' virtual/kernel u-boot-mkimage-native', '', d)}"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:mx8m-generic-bsp = "${@bb.utils.contains('DISTRO_FEATURES', 'falconmode', ' file://0001-imx8m-add-falcon-mode-support.patch', '', d)}"

do_compile[depends] += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'falconmode', 'virtual/kernel:do_deploy', '', d)} \
"

do_compile:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'falconmode', 'true', 'false', d)}; then
      cp ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE} ${BOOT_STAGING}

      make SOC=${IMX_BOOT_SOC_TARGET} ${KERNEL_TARGET}
      make SOC=${IMX_BOOT_SOC_TARGET} uImage
    fi
}

do_deploy:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'falconmode', 'true', 'false', d)}; then
      cp ${BOOT_STAGING}/${KERNEL_TARGET} ${DEPLOY_DIR_IMAGE}
      cp ${BOOT_STAGING}/${UBOOT_TARGET} ${DEPLOY_DIR_IMAGE}
      cp ${BOOT_STAGING}/uImage ${DEPLOY_DIR_IMAGE}

      cp ${DEPLOYDIR}/${BOOT_CONFIG_MACHINE}-${IMAGE_IMXBOOT_TARGET} ${DEPLOYDIR}/falcon-${BOOT_CONFIG_MACHINE}-${IMAGE_IMXBOOT_TARGET}
    fi
}
