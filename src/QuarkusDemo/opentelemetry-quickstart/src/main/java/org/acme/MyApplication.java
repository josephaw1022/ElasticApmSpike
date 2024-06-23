package org.acme;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import co.elastic.apm.attach.ElasticApmAttacher;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class MyApplication {

    @ConfigProperty(name = "apm.attach")
    private static boolean apmAttach = true;

    public static void main(String[] args) {

        if (apmAttach) {
            System.out.println("Attaching Elastic APM agent");
            ElasticApmAttacher.attach();
        }
        else { 
            System.out.println("Not attaching Elastic APM agent");
        }

        Quarkus.run(args);
    }
}
