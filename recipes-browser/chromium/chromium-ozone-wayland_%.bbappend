#enable gtk support in order to use native file picker
DEPENDS += " gtk+3"
GN_ARGS:remove = "use_gtk=false"
GN_ARGS:append = "use_gtk=true"