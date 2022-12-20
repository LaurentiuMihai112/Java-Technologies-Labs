✅(2p) Implement simple test cases to highlight the support offered by MicroProfile for writing resilient microservices.
Use the following: Fallback + Timeout and Retry(0.5p), CircuitBreaker(0.5p), Bulkhead thread-pool(0.5p) and semaphore(0.5p).
You should be able to invoke the annotated methods and analyze their behaviour.

✅(1p) Implement and test a health check procedure, in order to determine the readiness and the liveness of your service.

✅(1p) Use MicroProfile Metrics API in order to monitor the behaviour of your service.
Analyze the number of invocations and the response time for at least one method.

# MicroProfile generated Applications

MicroProfile Starter has generated 2 MicroProfile applications for you.

There are 2 projects generated so that the examples for the Rest Client and/or JWT Auth specification are more realistic in the sense that they actually call an endpoint within another service.

. In the `service-a` directory, you can find an application with the major parts of the application. This can be seen as the 'client'.
. In the `service-b` directory, you can find some endpoints which will be called by code within the client application. This can be seen as the 'backend'.

Have a look in the `readme.md` file in each directory which describes how each project can be built and run.


Once both projects are built and started, open your browser at the following URL to launch the test page and execute both projects:

    http://localhost:8080/index.html
