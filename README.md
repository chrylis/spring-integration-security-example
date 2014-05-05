Spring Integration security example
===================================

This is a simple project demonstrating the behavior of thread-scoped data
under different Spring Integration plans. A Spring profile, set in the
`Launcher`, controls whether Spring Integration is wired with direct or
`Executor`-based channels. In the case of direct channels, synchronous
operations are invoked on the calling thread, and thread-local request
and security contexts are available to wrapped service code. In the case
of `Executor` channels **or** asynchronous operations even with direct
channels, the thread-local contexts are unavailable.

Endpoints
---------

`/suspicious/say/{text}`

Says the text.

`/spying/controller/{fast|slow}`

Tries to determine the absolute URL of the suspicious controller either
synchronously or asynchronously.

`/spying/user/{fast|slow}`

Tries to determine the Spring Security identity of the logged-in user either
synchronously or asynchronously.