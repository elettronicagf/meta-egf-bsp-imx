#init EGF_DISTRO_VERSION with current tag of repo manifest

python () {
    import subprocess
    #searching tag in manifest repo
    try:
        tag = subprocess.check_output(
            "git -C %s/../../.repo/manifests tag --points-at HEAD" %
            d.getVar("EGF_LAYER_DIR"),
            shell=True,
            text=True
        ).strip()
    except:
        pass

    #if not found get commit id
    if not tag:
        try:
            tag = subprocess.check_output(
                "git -C %s/../../.repo/manifests rev-parse --short HEAD" %
                d.getVar("EGF_LAYER_DIR"),
                shell=True,
                text=True
            ).strip()
        except:
            tag="invalid_id"
    
    d.setVar("EGF_DISTRO_VERSION", tag)
}

