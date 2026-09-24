#init EGF_DISTRO_VERSION with current tag of repo manifest

python () {
    import subprocess
    value = d.getVar("EGF_DISTRO_VERSION")

    if value is not None:
        return
    bb.fatal(f"Missing EGF_DISTRO_VERSION")
}
