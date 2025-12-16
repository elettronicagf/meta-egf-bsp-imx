
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://99-unmanaged-uap0.conf"

# install configuration to not manage the uap0 interface
# this is to avoid conflicts with mlan0 (lags, instabilities etc.) when it is connected to a wifi network
do_install:append() {
	install -Dm 0644 ${WORKDIR}/99-unmanaged-uap0.conf ${D}${libdir}/NetworkManager/conf.d/99-unmanaged-uap0.conf
}