package com.ym.yitter.net;

import android.content.Context;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

/**
 * Created by Yuriy Myronovych on 21/08/2015.
 */
public class DataAccessTest extends TestCase {

    public void testInit() throws Exception {
        Context ctx = mock(Context.class);
        DataAccess.init(ctx);

        assertNotNull(DataAccess.getInstance());
        assertNotNull(DataAccess.getInstance().getClient());
    }
}