#!/bin/sh
echo "==========================================================" > /dev/console

# launch application here, in background. E.g.
# /usr/bin/myapp &
cat /etc/egf.brga > /dev/fb0

exec /lib/systemd/systemd
