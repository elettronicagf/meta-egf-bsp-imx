

DESCRIPTION = " \
This package install egf-init.service \
and related depenencies. \
It's a systemd service that run an \
initialization script. \
It's used in some machine to init \
particular HW sections like LTE/WiFI modules \
"
DEPENDS = " libgpiod i2c-tools \
           ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)} \
          "


SRC_URI = "\
           file://egf_init \
           file://egf_init.service \
           "

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


inherit systemd

SYSTEMD_SERVICE:${PN} = "egf_init.service"


do_install:append () {

    install -d ${D}${sbindir}/
    install -m 0755 ${WORKDIR}/egf_init ${D}${sbindir}/egf_init

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/egf_init.service ${D}${systemd_unitdir}/system
}