package dao;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by user on 7/8/15.
 */
public class MorphiaConfigSpec {
    @Test
    public void itShouldReturnDatastore(){
        MorphiaConfig morphiaConfig = new MorphiaConfig();
        Datastore datastore = morphiaConfig.getDatastore();
        assertThat(datastore,notNullValue());
    }

}
