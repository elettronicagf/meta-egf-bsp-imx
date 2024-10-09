FILESEXTRAPATHS:prepend := "${THISDIR}/files:"



do_install:append () {
    sed -i -e "s#PASSWORD_FIELD#${EGF_UPDATE_PASSWORD}#" ${D}/init
}

