package com.backupapi.api.responses;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import de.bechte.junit.runners.context.HierarchicalContextRunner;

import java.util.UUID;

import javax.ws.rs.core.Response.Status;

@RunWith(HierarchicalContextRunner.class)
public class ResponseFactoryTest {
  private AcceptedResponse acceptedResponse;
  private CreatedResponse createdResponse;
  private SucceededResponse succeededResponse;
  private ForbiddenResponse forbiddenResponse;
  private UnauthorizedResponse unauthorizedResponse;

  @Before
  public void setup () {
    this.acceptedResponse = ResponseFactory.call(new AcceptedResponse());
    this.createdResponse = ResponseFactory.call(new CreatedResponse());
    this.succeededResponse = ResponseFactory.call(new SucceededResponse());
    this.forbiddenResponse = ResponseFactory.call(new ForbiddenResponse());
    this.unauthorizedResponse = ResponseFactory.call(new UnauthorizedResponse());
  }

  public class InheritedResponseMethods {
    Response[] responsePool;

    @Before
    public void setup () {
      this.responsePool = new Response[] {
        acceptedResponse,
        createdResponse,
        succeededResponse,
        forbiddenResponse,
        unauthorizedResponse
      };
    }

    @Test
    public void testGetRequestId () {
      System.out.println("*****************************");
      for (Response response : responsePool) {
        UUID uuid = UUID.fromString(response.getRequestId());
        Assert.assertEquals(response.getRequestId(), uuid.toString());
      }
    }

    @Test
    public void testGetTimestamp () {
      for (Response response : responsePool) {
        Assert.assertTrue(Math.abs(response.getTimestamp() - System.currentTimeMillis()) < 100);
      }
    }
  }

  public class OverridedResponseMethods {
    @Test
    public void testGetAcceptedStatus () {
      Assert.assertEquals(acceptedResponse.getStatus(), Status.ACCEPTED.getStatusCode());
    }

    @Test
    public void testGetCreatedStatus () {
      Assert.assertEquals(createdResponse.getStatus(), Status.CREATED.getStatusCode());
    }

    @Test
    public void testGetSucceededStatus () {
      Assert.assertEquals(succeededResponse.getStatus(), Status.OK.getStatusCode());
    }

    @Test
    public void testGetForbiddenStatus () {
      Assert.assertEquals(forbiddenResponse.getStatus(), Status.FORBIDDEN.getStatusCode());
    }

    @Test
    public void testGetUnauthorizedStatus () {
      Assert.assertEquals(unauthorizedResponse.getStatus(), Status.UNAUTHORIZED.getStatusCode());
    }
  }
}
