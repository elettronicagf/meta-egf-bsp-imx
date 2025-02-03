FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
PR = "r1"
SRC_URI:append = " \
                    file://0001-Applicata-patch-weston-imx-12.0.3-LF6.6.3_1.0.0-clon.patch \
                    file://0002-Clone-su-HDM-rotated-and-centered.patch \
                    file://0003-commented-unused-API.patch \
                    file://0004-Added-option-in-weston.ini-to-enable-custom-clone-mo.patch \
                "