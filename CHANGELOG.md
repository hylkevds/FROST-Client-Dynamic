# Changelog

## Development Version

**Updates**
* Added checks when setting or getting properties, throw IllegalArgumentException
  when the EntityType does not have the property.


## Release Version 2.1

**Updates**
* Replaced the concept of Id with the concept of PrimaryKey.
  Primary keys point to one or more properties that can have any name.
* Fixed 403 being returned as 401.
* Bumped dependency versions


## Release Version 2.0

**Updates**
* Complete redesign of FROST-Client to become data model agnostic.

