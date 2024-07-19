#meta-qt6/classes/populate_sdk_qt6.bbclass
#meta-qt6/recipes-qt/packagegroups/packagegroup-qt6-modules.bb
#
RDEPENDS:${PN}:remove = "qtpdf"
RDEPENDS:${PN}:remove = "qtdoc"
