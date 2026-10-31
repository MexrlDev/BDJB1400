# tools/verify_hashes.ps1
$root = Split-Path $PSScriptRoot -Parent
$checks = @(
    @{P="assets\bonk\hen.bin";E="29ec095fbede651e29954de43411599093138791e62105aaee6fdced58edec06";N="hen.bin"},
    @{P="assets\x\offline\Hx1400.class";E="e597710078e9afab5b24f4c22ea31fe7e5a5643b602b06c2866273b61c9c57e9";N="Hx1400.class"},
    @{P="assets\x\offline\EkIKZR.class";E="df30bfbd25c3cdbe67965d907d575efb562738cf29c258895a698ff2ef525137";N="EkIKZR.class"},
    @{P="assets\x\offline\AXZXkk.class";E="2362afc9efa07a688169b9121d9e5f8bdacbead58f87cafd23fdddffea58b429";N="AXZXkk.class"},
    @{P="assets\x\offline\J196rR.class";E="f59953027867d15d1434179991feeeb94bf274422f742e739067fd65400a264d";N="J196rR.class"}
)
$ok = 0; $fail = 0
foreach ($c in $checks) {
    $full = Join-Path $root $c.P
    if (-not (Test-Path $full)) { Write-Host "$($c.N) MISSING"; $fail++; continue }
    $a = (Get-FileHash $full -Algorithm SHA256).Hash.ToLower()
    if ($a -eq $c.E) { Write-Host "$($c.N) MATCH"; $ok++ }
    else { Write-Host "$($c.N) MISMATCH"; $fail++ }
}
Write-Host ""
Write-Host "Result: $ok passed, $fail failed"
exit $fail