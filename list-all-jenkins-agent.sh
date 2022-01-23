#!/usr/bin/env bash
# set -o errexit
set -o pipefail
set -o nounset

jenkins_url=${1?}
jenkins_credential=${2?}
vm_user=${3?}
vm_pass=${4?}
for agent_name in $(curl "$jenkins_url/computer/api/json" | jq -r .computer[].displayName | grep -v master); do
    ip=$(curl -u "$jenkins_credential" -d "script=println InetAddress.localHost.hostAddress" \
              "$jenkins_url/computer/$agent_name/scriptText" 2>/dev/null | sed -e 's/^[ \t]*
    echo "agent_name: $agent_name, ip: $ip"
    for command in "cat /proc/loadavg" "docker version | grep '^ Version:'" "df -h /var/lib"; do
        sshpass -p "$vm_pass" ssh -o LogLevel=error -o StrictHostKeyChecking=no "$vm_user@$ip" "$command"
    done
done

