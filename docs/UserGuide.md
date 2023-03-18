---
layout: page
title: User Guide
---
# EduMate User Guide

EduMate is a desktop app designed for NUS students to manage their academic and social lives.

## Using this Guide
If this is the first time you are using this user guide, we highly recommend you to read the [Overview](#overview) section. Otherwise,

* If you are running EduMate for the first time, please take a look at our [Quick Start](#quick-start) guide.
* If you want to learn to use EduMate, do check out our [Commands](#) section for a detailed guide.
* If you want to contribute to this project, please refer to our [Developer Guide](https://ay2223s2-cs2103t-w14-2.github.io/tp/DeveloperGuide.html).

## Table of Contents
1. [**Using this Guide**](#using-this-guide)
2. [**Table of Contents**](#table-of-contents)
3. [**Overview**](#overview)
   1. [**What is EduMate?**](#what-is-edumate)
   2. [**Understanding the Symbols and Colours**](#understanding-the-symbols-and-colours)
   3. [**Glossary**](#glossary)
4. [**Quick Start**](#quick-start)
   1. [**System Requirements**](#system-requirements)
   2. [**Installation Instructions**](#installation-instructions)
5. [**User Interface**](#user-interface)
   1. [**Person List**](#person-list)
   2. [**Person Profile**](#person-profile)
   3. [**Command Line**](#command-line)
   4. [**Command Result**](#command-result)
6. [**Commands**](#commands)
   1. [**How to interpret the command format**](#how-to-interpret-the-command-format)
   2. [**Arguments**](#arguments)
      1. [**Name**](#name)
      2. [**Phone Number**](#phone-number)
      3. [**Email Address**](#email-address)
      4. [**Home Address**](#home-address)
      5. [**Telegram Handle**](#telegram-handle)
      6. [**Group**](#group)
      7. [**Module**](#module)
   3. [**Basic Commands**](#basic-commands)
      1. [**Exit the application `exit`**](#exit-the-application-exit)
      2. [**Get help `help`**](#get-help-help)
      3. [**List all contacts `list`**](#list-all-contacts-list)
      4. [**Add a contact `add`**](#add-a-contact-add)
      5. [**View a contact's profile `view`**](#view-a-contacts-profile-view)
      6. [**Edit a contact's details `edit`**](#edit-a-contacts-details-edit)
      7. [**Delete a contact `delete`**](#delete-a-contact-delete)
      8. [**Add a label to a contact `tag`**](#tag-a-module-to-an-existing-contact--tag)
      9. [**Remove a label from a contact `tag`**](#untag-a-module-from-an-existing-contact--untag)
   4. [**Advanced Commands**](#advanced-commands)
      1. [**Find contacts that match your criteria `find`**](#locate-persons-by-keywords--find)
      2. [**Sort contacts based on your criteria `sort`**](#sort-contacts-based-on-your-criteria-sort)
      3. [**Suggest places to eat with your contacts `eat`**](#suggest-places-to-eat-with-your-contacts-eat)
      4. [**Suggest places to study with your contacts `study`**](#suggest-places-to-study-with-your-contacts-study)
      5. [**Suggest places to meet with your contacts `meet`**](#suggest-places-to-meet-with-your-contacts-meet)
      6. [**Save a copy of EduMate `save`**](#save-a-copy-of-edumate-save)
      7. [**Load a copy of EduMate `load`**](#load-a-copy-of-edumate-load)
      8. [**View your past command history `history`**](#view-your-past-command-history-history)
   5. [**Miscellaneous Commands**](#miscellaneous-commands)
      1. [**Clear the contents of EduMate `clear`**](#clear-the-contents-of-edumate-clear)
      2. [**Generate a random EduMate `sample`**](#generate-a-random-edumate-sample)
7. [**FAQ**](#faq)
8. [**Summary**](#summary)
   1. [**Prefixes**](#prefixes)
   2. [**Command Summary**](#command-summary)
9. [**Troubleshooting**](#troubleshooting)
   1. [**How to check your Java version**](#how-to-check-your-java-version)

--------------------------------------------------------------------------------------------------------------------

## Overview

The following subsections explain the overarching functionality of EduMate, as well as the various terminology we will use in this guide.

### What is EduMate?

{to be filled}

### Understanding the Symbols and Colours

Here is a breakdown of the different symbols and colours we will use throughout this guide.

<div markdown="span" class="alert alert-info">

:information_source: Useful information for you to know.

</div>

<div markdown="span" class="alert alert-success">

:bulb: Tips and tricks to enhancing your experience in using EduMate.

</div>

<div markdown="span" class="alert alert-danger">

:warning: Warnings for you to heed so that EduMate works as intended.

</div>

### Glossary

[Return to the top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Quick start

### System Requirements

Here is everything you need to install and set up EduMate. For the best possible experience, we recommend that you run the application on the following supported operating systems:

* Windows
* macOS
* Linux

Do also ensure that you have Java `11` or above installed in your Computer. If you don’t already have Java `11` or above on your system, head over to [Oracle’s Java download page](https://www.oracle.com/java/technologies/downloads/). To check whether your Java version is compatible with EduMate, please refer to [this section]().

### Installation Instructions

1. Download the latest `eduMate.jar` from [here](https://github.com/AY2223S2-CS2103T-W14-2/tp/releases).

2. Copy the file to the folder you want to use as the _home folder_ for your EduMate.

3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar eduMate.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
   <br>
4. Type the command in the command box and press Enter to execute it.<br>
   Some example commands you can try:

   * `help`: Displays a link leading to this User Guide.
   
   * `list`: Lists the contacts in EduMate.

   * `view 1`: View the first contact in the profile window.

   * `exit`: Ends the application.
   <br>
    
[Return to the top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## User Interface

{to be filled in}

### Person List

{to be filled in}

### Person Profile

{to be filled in}

### Command Line

{to be filled in}

### Command Result

{to be filled in}

[Return to the top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Commands

| Command   | Description                                                                                  |
|-----------|----------------------------------------------------------------------------------------------|
| `exit`    | [Exit the application](#exit-the-application-exit)                                           |
| `help`    | [Get help](#get-help-help)                                                                   |
| `list`    | [List all contacts](#list-all-contacts-list)                                                 |
| `add`     | [Add a contact](#add-a-contact-add)                                                          |
| `view`    | [View a contact's profile](#view-a-contacts-profile-view)                                    |
| `edit`    | [Edit a contact's details](#edit-a-contacts-details-edit)                                    |
| `delete`  | [Delete a contact](#delete-a-contact-delete)                                                 |
| `tag`     | [Add or remove a label from a contact](#tagging-a-module-to-an-existing-contact--tag)        |
| `find`    | [Find contacts that match your criteria](#locating-persons-by-keywords--find)                |
| `sort`    | [Sort contacts based on your criteria](#sort-contacts-based-on-your-criteria-sort)           |
| `save`    | [Save a copy of EduMate](#save-a-copy-of-edumate-save)                                       |
| `load`    | [Load a copy of EduMate](#load-a-copy-of-edumate-load)                                       |
| `meet`    | [Suggest places to meet with your contacts](#suggest-places-to-meet-with-your-contacts-meet) |
| `history` | [View your past command history](#view-your-past-command-history-history)                    |
| `clear`   | [Clear the contents of EduMate](#clear-the-contents-of-edumate-clear)                        |
| `sample`  | [Generate a random EduMate](#generate-a-random-edumate-sample)                               |

### How to interpret the command format

<div markdown="block" class="alert alert-info">

**:information_source: Basic Command Formats**<br>

* The first word is the type of command that you are running.<br>
  e.g. for the command `delete 5`, we are running the `delete` command.

* We use [Prefixes]() like `p/` and `g/` to label our arguments for the command. Additionally, we use `z/` to denote any prefix.<br>
  e.g. `edit 2 n/Steven Tan` runs the `edit` command with an index of 2 and an `n/` argument of Steven Tan.

* Words in `UPPER_CASE` are arguments you are meant to fill in.<br>
  e.g. in `view n/NAME`, you can fill in the `NAME` argument like `view n/Tan Kah Kee`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [g/GROUP]` can be used as `n/Tan Kah Kee g/Friend` or as `n/Tan Kah Kee`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[m/MODULE]…​` can be used as ` ` (i.e. 0 times), `m/`, `m/CS2108 m/CS2101` etc.

</div>

<div markdown="block" class="alert alert-info">

**:information_source: Additional Command Formats**<br>

* Unless otherwise specified, arguments can be in any order.<br>
  e.g. if the command specifies `n/NAME a/ADDRESS`, `a/ADDRESS n/NAME` is also acceptable.

* Unnecessary arguments will be ignored.<br>
  e.g. `help 123` will be interpreted as `help`.

</div>

<div markdown="block" class="alert alert-danger">

**:warning: Invalid Command Formats**<br>

* Indices must be positive numbers.<br>
  e.g. `delete one` is not an accepted command.

* [Prefixes]() must be preceded by a space.<br>
  e.g. `sort m/an/Tan` is not an accepted command for prefixes `m/` and `n/`.

</div>

[Return to the top](#table-of-contents)

## Arguments

A contact's attributes can be categorised into two types: _single-valued_ and _multi-valued_. A contact may have only one of each single-valued attribute, such as name, phone number, email address, Telegram handle, and home address. Conversely, a contact may have any number of multi-valued attributes, such as groups and modules.

### Name

Description: The name of the person.<br>
Pattern: `n/NAME`<br>
Rules: `NAME` should only contain alphanumeric characters and spaces. It **must also be unique**.<br>
Example: `n/Wen Li`

### Phone Number

Description: The phone number of the person.<br>
Pattern: `p/PHONE_NUMBER`<br>
Rules: `PHONE_NUMBER` should only contain numbers, and be at least 3 digits long.<br>
Example: `p/89229358`

### Email Address

Description: The email address of the person.<br>
Pattern: `e/EMAIL`<br>
Rules: `EMAIL` should be of the form `local@domain`, where
* `local` consists of only alphanumeric and the special characters `+`, `_`, `.`, `-`.
* `domain` {can someone fill up}<br>
Example: `e/wenli@gmail.com`

### Home Address

Description: The name of the **closest MRT station** to their home.<br>
Pattern: `a/ADDRESS`<br>
Rules: `ADDRESS` should be the name of a valid MRT station.<br>
Example: `a/Boon Lay`

### Telegram Handle

Description: The telegram handle of the person.<br>
Pattern: `t/TELEGRAM`<br>
Rules: `TELEGRAM` should start with an `@` symbol.<br>
Example: `t/@wenli`

### Group

Description: The group that you and the person belong to.<br>
Pattern: `g/GROUP`<br>
Rules: `GROUP` should only contain alphanumeric characters.<br>
Example: `g/Groupmate`

### Module

Description: The name of the module the person is taking.<br>
Pattern: `m/MODULE`<br>
Rules: `MODULE` should be the name of a valid NUS module.<br>
Example: `m/CS2107`

## Basic Commands

### Exit the application `exit`

Shows a message explaining how to access the help page.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
* you want to end the program.
* you are done using EduMate.

</div>

Formats:
* `exit`

What you should see:<br>
{Explanation}

Examples:
* `exit`

### Get help `help`

Shows a message explaining how to access the help page.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
* you are unsure of how to use EduMate.
* you need a refresher on how to use EduMate's commands.

</div>

Formats:
* `help`

What you should see:<br>
![help message](images/helpMessage.png)<br>
{Explanation}

Examples:

### List all contacts `list`

Shows a message explaining how to access the help page.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
* you want to view all your contacts at once.

</div>

Formats:
* `list`

What you should see:<br>
{GUI}
{Explanation}

Examples:

### Add a contact `add`

Adds a contact to the EduMate.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
* you want to add a new contact to your list.

</div>

Formats:
* `add n/NAME p/PHONE a/ADDRESS e/EMAIL t/TELEGRAM [g/GROUP]…​ [m/MODULE]…​`: {fill in}

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:
* `add n/Wen Li p/89229358 a/Boon Lay e/wenli@gmail.com t/@wenli g/Groupmate m/CS2101 m/CS2107`: Adds a contact with the following details:
  * Name: `Wen Li`
  * Phone number: `89229358`
  * Address: `Boon Lay`
  * Email: `wenli@gmail.com`
  * Telegram handle: `@wenli`
  * Groups: `Groupmate`
  * Modules: `CS2101`, `CS2107`

### View a contact's profile `view`

You can use the view command to look up their current information, or their contact's information on the Panel to the right.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
* you want to view the profile of a particular contact in the list.
* you want to view your information.

</div>

Formats:
* `view`: Views your profile.
* `view INDEX`: Views the contact at index `INDEX`.
* `view n/NAME`: Views the contact with name `NAME`.

What you should see:<br>

<img src="images/userprofile.png" style="width:50%; margin: 0 25%">
<div style="width:50%; margin: 0 25%; text-align:center">
    <code>view</code>: User's own profile displayed on the panel
</div>
<br>

<img src="images/fifthPersonProfile.png" style="width:50%; margin: 0 25%">
<div style="width:50%; margin: 0 25%; text-align:center">
    <code>view 5</code>: The profile of the 5th person indexed by EduMate will be displayed on the panel
</div>
<br>

<img src="images/charlesWindsor.png" style="width:50%; margin: 0 25%">
<div style="width:50%; margin: 0 25%; text-align:center">
    <code>view n/Charles Windsor</code>: The profile of Charles Windsor will be displayed on the panel
</div>
<br>

Examples:


### Edit a contact's details `edit`

Edits the profile of a contact.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
* you want to edit a particular field of a contact in the list.
* you want to edit several fields of a contact in the list.

</div>

Formats:
* `edit z/`: Edits the `z` field for your profile.
* `edit INDEX z/FIELD`: Edits the `z` field for the contact at the specified `INDEX`.
* `edit INDEX [z/FIELD]…​`: Edits multiple fields at the same time.

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:
* `edit p/89229358`: Changes your phone number to `89229358`.
* `edit 4 n/Wen Qing`: Changes the name of the fourth contact to `Wen Qing`.
* `edit 3 p/89229358, a/Boon Lay`: Changes the phone and address of the third contact.

### Delete a contact `delete`

Edits the profile of a contact.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
* you no longer want a contact in the EduMate.

</div>

Formats:
* `delete INDEX`: removes the contact at the specified `INDEX`.

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:
* `delete 4`: removes the fourth contact from the EduMate.

### Tag a module to an existing contact : `tag`

Adds module tag(s) to an existing contact.

Formats: 
* `tag CONTACT_INDEX m/MODULE_TAG`
* `tag m/MODULE_TAG`

Example of usage: `tag 3 m/CS2103T`
```
Name: John Doe (User)
Modules: [CS2101 , MA2104 , MA3252 , CFG1002]
```

Expected outcome for CLI:
```
Module(s) tagged to Person!
Name: John Smith
Modules: [CS2100, CS2101, CS2102, CS2103T]
Module(s) in common: [CS2101, CS2103T]
```
Description of outcome: 

CS2103T is added to John Doe's list of modules. Assuming the user also takes CS2101 and CS2103T, which are represented as the modules in common.

Example of usage: tag m/CS2103T
```
Name: John Doe (user)
Modules: [CS2101, MA2104, MA3252, CFG1002]
```

Expected outcome for CLI:
```
Module(s) tagged to Person!
Name: John Doe
Modules: [CS2101, CS2103T, MA2104, MA3252, CFG1002]
```
Description of outcome:

CS2103T is added to John Doe's, the user, list of modules.

### Untag a module from an existing contact : `untag`

Removes a module tag from an existing contact.

Formats: 
* `untag CONTACT_INDEX m/MODULE_TAG`
* `untag m/MODULE_TAG`

Example of usage: `untag 3 m/CS2103T`
```
Name: John Doe (User)
Modules: [CS2101, MA2104, MA3252, CFG1002]
```

Expected outcome for CLI:
```
"Module(s) untagged to Person!
Name: John Smith
Modules: [CS2100, CS2101, CS2102]
Module(s) in common: [CS2101]
```
Description of outcome: 

CS2103T is removed from John Doe's list of modules. Assuming the user also takes CS2101, which is represented as the modules in common.

Example of usage: untag m/CS2103T
```
Name: John Doe (user)
Modules: [CS2101, CS2103T, MA2104, MA3252, CFG1002]
```

Expected outcome for CLI:
```
"Module(s) untagged to Person!
Name: John Doe
Modules: [CS2101, MA2104, MA3252, CFG1002]
```
Description of outcome:

CS2103T is added to John Doe's, the user, list of modules.

## Advanced Commands

### Locate persons by keywords : `find`

Finds persons whose specified fields contain any of the given keywords.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
*

</div>

Formats:
* `find PREFIX/KEYWORD [MORE_KEYWORDS]`

What you should see:<br>
{GUI}<br>
{Explanation}

<div markdown="block" class="alert alert-success">

:bulb: Tips on usage<br>
* The search is case-insensitive. e.g hans will match Hans
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans
* Only the field specified by the prefix is searched. e.g. n/ means only the name field is searched
* Words matching the first part of the string will be matched e.g. Han will match Hans
* Persons matching at least one keyword will be returned (i.e. OR search). e.g. Hans Bo will return Hans Gruber, Bo Yang

</div>

Examples:
* `find m/CS2103T CS2109S` returns all persons with modules CS2103T or CS2109S
* `find n/Edward Richards` returns all persons with names Edward or Richards
* `find p/9093` returns all persons with phone numbers starting with 9093

### Sort contacts based on your criteria `sort`

Sorts contacts in the list by certain criteria. For example, you could sort the contacts by phone number and their names.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
* 

</div>

Formats:
* `sort`: Sorts the contacts based on their index in the EduMate.
* `sort z/a`: Sorts by the `z` field in **ascending order**.
* `sort z/d`: Sorts by the `z` field in **descending order**.
* `sort z/`: Sorts by the `z` field in its **default ordering**.
* `sort z1/ z2/`: Sorts by the `z1` field, then if two contacts have the same `z1` field, sort by the `z2` field.
* `sort [z/]…​`:  Sorts by multiple conditions.

<div markdown="block" class="alert alert-info">

:information_source: Here is how we compare the fields:
* The `NAME`, `PHONE`, `ADDRESS`, `EMAIL` and `TELEGRAM` attributes are sorted in alphabetical order. By default, we sort in **ascending order**.
* For the `GROUP` attribute, we sort contacts based on the number of groups they are part of. By default, we sort in **descending order**.<br>
  e.g. if Alex has 2 groups `TA` and `NS`, and Ben has 1 group `NS`, Alex will appear before Ben in the default ordering.
* For the `MODULE` attribute, we sort contacts based on the number of modules they share with you. By default, we sort in **descending order**.<br>
  e.g. if Alex has 2 modules `CS1231S` and `CS1101S`, Ben has 2 modules `CS1231S` and `MA2001`, and you have 2 modules `CS1101S` and `MA1521`, then Alex has 1 common module while Ben has no common modules, so Alex will appear before Ben in the default ordering.

</div>

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:
* `sort n/d`: Sorts contacts by name in **descending order**.
* `sort a/a`: Sorts contacts by address in **ascending order**.
* `sort g/d n/d`: Sorts contacts by **descending** number of groups, using the names as tie breaks.

### Save a copy of EduMate `save`

Saves the current state of the EduMate into a file.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
* you want to save your progress.
* you want to play around with the functionality without changing anything permanently.

</div>

Formats:
* `save FILE_NAME`: Saves the EduMate into a file called `FILE_NAME.json`.

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:
* `save backup`: Saves the EduMate into the `backup.json` file.

### Load a copy of EduMate `load`

Loads the contents of a save file into EduMate.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
* you want to retrieve your save progress.

</div>

Formats:
* `load FILE_NAME`: Loads the EduMate from a file called `FILE_NAME.json`.

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:
* `load backup`: Loads the EduMate from the file named `backup.json`.

### Suggest places to eat with your contacts `eat`

Edits the profile of a contact.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
*

</div>

Formats:
*

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:

### Suggest places to study with your contacts `study`

Edits the profile of a contact.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
*

</div>

Formats:
*

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:

### Suggest places to meet with your contacts `meet`

Edits the profile of a contact.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
*

</div>

Formats:
*

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:

### View your past command history `history`

Edits the profile of a contact.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
*

</div>

Formats:
*

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:

## Miscellaneous Commands

### Clear the contents of EduMate `clear`

Edits the profile of a contact.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
*

</div>

Formats:
*

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:

### Generate a random EduMate `sample`

Edits the profile of a contact.

<div markdown="block" class="alert alert-info">

:information_source: You can use this if:
*

</div>

Formats:
*

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:


## FAQ

**Q**: Can I add multiple module tags to a user
**A**: Yes, you can add multiple tags to a single user by using the "Module Tagging" feature multiple times.

**Q**: Can I edit my own profile?
**A**: It will not be in v1.02, but it may be possible for future iterations.


## Summary

### Prefixes

These prefixes are for labelling arguments when you run our commands.

| Prefix | Meaning         | Usage                                                                                          | Example             |
|--------|-----------------|------------------------------------------------------------------------------------------------|---------------------|
| `n/`   | Name            | A string that does not contain special characters                                              | `n/Wen Li`          |
| `p/`   | Phone number    | A number with a length of at least 3                                                           | `p/89229358`        |
| `e/`   | Email           | A valid email with a local part and a domain                                                   | `e/wenli@gmail.com` |
| `t/`   | Telegram handle | A telegram handle starting with `@`                                                            | `t/@wenli`          |
| `a/`   | Address         | The name of an MRT station in Singapore                                                        | `a/Boon Lay`        |
| `g/`   | Group           | A string that does not contain special characters                                              | `g/Friend`          |
| `m/`   | Module          | A valid module code with a 2-4 letter prefix, a 4 number middle portion and an optional suffix | `m/CS2103T`         |

### Command Summary

| Action                                                            | Format                                               | Example                                                                |
|-------------------------------------------------------------------|------------------------------------------------------|------------------------------------------------------------------------|
| [exit](#exit-the-application-exit)                                | `exit`                                               | `exit`                                                                 |
| [help](#get-help-help)                                            | `help`                                               | `help`                                                                 |
| [list](#list-all-contacts-list)                                   | `list`                                               | `list`                                                                 |
| [add](#add-a-contact-add)                                         | `add z/…​`                                           | `add n/Wen Qing p/87746543 e/wenqing@gmail.com t/@wenqing a/Boon Keng` |
| [view](#view-a-contacts-profile-view)                             | `view INDEX`, `view n/NAME`                          | `view 5`, `view n/Wen Qing`                                            |
| [edit](#edit-a-contacts-details-edit)                             | `edit INDEX z/FIELD`, `edit z/FIELD`                 | `edit 3 n/Wen Qing`, `edit p/87746543`                                 |
| [delete](#delete-a-contact-delete)                                | `delete INDEX`                                       | `delete 6`                                                             |
| [tag/untag](#tagging-a-module-to-an-existing-contact--tag)        | `tag`                                                | `tag 4 g/Friend`, `tag m/CS1231S`                                      |                                     |
| [find](#locating-persons-by-keywords--find)                       | `find z/KEYWORD`                                     | `find n/Tan`                                                           |
| [sort](#sort-contacts-based-on-your-criteria-sort)                | `sort [z/]…​`, `sort [z/a]…​`, `sort [z/d]…​`        | `sort n/d`, `sort g/ p/d`                                              |
| [save](#save-a-copy-of-edumate-save)                              | `save FILE_NAME`                                     | `save backup`                                                          |
| [load](#load-a-copy-of-edumate-load)                              | `load FILE_NAME`                                     | `load backup`                                                          |
| [meet/eat/study](#suggest-places-to-meet-with-your-contacts-meet) | `meet [INDEX]…​`, `eat [INDEX]…​`, `study [INDEX]…​` | `meet 1 4 6`, `eat 7 2 4`, `study 6 4 5`                               |
| [history](#view-your-past-command-history-history)                | `history`                                            | `history`                                                              |
| [clear](#clear-the-contents-of-edumate-clear)                     | `clear`                                              | `clear`                                                                |
| [sample](#generate-a-random-edumate-sample)                       | `sample SIZE`                                        | `sample 45`                                                            |

## Troubleshooting

### How to check your Java version
{fill in}

