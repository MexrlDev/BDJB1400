# tools/extract_iso.ps1
# Extracts hen.iso to a folder. Handles UDF mount or falls back to manual.
param(
    [string]$IsoPath = "C:\Users\DevPc\Desktop\JB\hen.iso",
    [string]$OutDir  = "C:\Users\DevPc\Desktop\JB\extracted_fresh"
)

if (-not (Test-Path $IsoPath)) { Write-Error "ISO not found: $IsoPath"; exit 1 }
Write-Host "ISO: $IsoPath"
Write-Host "Out: $OutDir"

if (-not (Test-Path $OutDir)) {
    New-Item -ItemType Directory -Force -Path $OutDir | Out-Null
}

Write-Host "Mounting ISO..."
$mount = Mount-DiskImage -ImagePath $IsoPath -PassThru
if (-not $mount) { Write-Error "Mount failed"; exit 1 }

$drive = ($mount | Get-Volume).DriveLetter
Write-Host "Mounted at ${drive}:"

Write-Host "Copying files..."
Copy-Item "${drive}:\*" $OutDir -Recurse -Force

Write-Host "Dismounting..."
Dismount-DiskImage -ImagePath $IsoPath | Out-Null

Write-Host ""
Write-Host "Done. Extracted to: $OutDir"
Write-Host "Files: $((Get-ChildItem $OutDir -Recurse -File).Count)"