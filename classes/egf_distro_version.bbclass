#force image rebuilt if EGF_DISTRO_VERSION changes
do_rootfs[vardeps] += "EGF_DISTRO_VERSION"
do_image_complete[vardeps] += "EGF_DISTRO_VERSION"

#check EGF_DISTRO_VERSION has been defined
python () {
    import subprocess
    value = d.getVar("EGF_DISTRO_VERSION")

    if value is not None:
        return
    bb.fatal(f"Missing EGF_DISTRO_VERSION")
}
