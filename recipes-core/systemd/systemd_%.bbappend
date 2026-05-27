
# set our custom script as init service, to leverage falcon mode fast startup
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://egf-init.sh \
	    file://egf.brga"

do_install:append() {
	if ${@bb.utils.contains('DISTRO_FEATURES', 'falconmode', 'true', 'false', d)}; then
	    install -m 0755 ${WORKDIR}/egf-init.sh ${D}${base_sbindir}
	    ln -sf ${base_sbindir}/egf-init.sh ${D}${base_sbindir}/init
	    install -m 0755 ${WORKDIR}/egf.brga ${D}${sysconfdir}
	fi
}

FILES:${PN} += "${base_sbindir}/egf-init.sh ${sysconfdir}/egf.brga"