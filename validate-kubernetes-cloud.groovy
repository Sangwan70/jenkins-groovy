#!groovy

import org.csanchez.jenkins.plugins.kubernetes.*
import jenkins.model.*
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
try {
    def j = Jenkins.getInstance()
        def client = j.clouds[0].connect()
        client.pods().list();
} catch (KubernetesClientException e) {
    println("Error testing connection %s" + e.getMessage())
} catch (Exception e) {
    println("Error testing connection %s" + e.getMessage())
}
