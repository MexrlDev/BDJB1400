# PHONE_HOME_ANALYSIS

Proof that tester.f5rvoice.com:1992 is dead code.
Full detail: README.md section 15.

4 independent layers of blockage:

Layer 1 - unreachable from InitXlet
  InitXlet calls only Boot.exec. Never Boot.run.

Layer 2 - unreachable from any cmd script
  cmd-0043.bin references run but targets Hx1400 (cls=30), not Boot.

Layer 3 - malformed if reached
  InitXlet:580 sets Boot.EP = discRoot (a file: URL).
  Boot.io() casts to HttpURLConnection -> ClassCastException.

Layer 4 - BD-J permission denies
  bluray.InitXlet.perm grants no Connector.http.
  Any http:// openConnection() -> SecurityException.

Conclusion: vestigial upstream BD-JB code, preserved to avoid
changing Boot.class SHA-256 hash (verified by InitXlet).
