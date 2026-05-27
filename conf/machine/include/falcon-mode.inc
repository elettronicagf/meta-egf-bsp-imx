# falcon mode stuff
UBOOT_TARGET = "u-boot-atf-container.img"
UBOOT_TARGET:mx8m-generic-bsp = "u-boot.itb"
UBOOT_TARGET:mx8mm-generic-bsp = "u-boot-ddr4-evk.itb"

KERNEL_TARGET = "kernel-atf-container.img"
KERNEL_TARGET:mx8m-generic-bsp = "kernel-atf.itb"

MACHINE_FEATURES:remove = "optee"

# to enable falcon mode, add in local.conf file the following lines
# DISTRO_FEATURES:append = " falconmode"
# BBMULTICONFIG = "mfgtool"