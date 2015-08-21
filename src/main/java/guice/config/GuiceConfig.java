package guice.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import dao.MorphiaConfig;
import dao.ProductDao;
import resource.ProductResource;

import java.util.HashMap;

public class GuiceConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {

        return Guice.createInjector(new JerseyServletModule() {

            @Override
            protected void configureServlets() {
                bind(ProductResource.class);
                bind(ProductDao.class);
                bind(MorphiaConfig.class);
                HashMap<String, String> options = new HashMap<>();
                options.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
                serve("/rest/*").with(GuiceContainer.class ,options);
            }
        });
    }
}
