# Changelog

## 0.4.0

- Migrated to AndroidX. If you're still using the old support libraries, **DO NOT UPGRADE**.
- Added auto-init of library. **Breaking API change**: constructor is no longer public, call `AppFocusProvider.getInstance()` instead.

