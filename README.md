The test TestApp started failing after upgrading to 1.0.1.RELEASE of io.r2dbc:r2dbc-mssql. Reverting to 1.0.0.RELEASE resolves the error.
The exception being thrown is

```
io.r2dbc.mssql.ExceptionFactory$MssqlNonTransientException: Could not find prepared statement with handle 1073741825.
```

Reverting changes in this commit https://github.com/r2dbc/r2dbc-mssql/commit/07cb863d257909a7ea961c00c5a281f174200daf resolves the error.
So it seems the change related to `preferCursoredExecution` is causing this issue.