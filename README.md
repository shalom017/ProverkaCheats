# 🛡️ Proverka Minecraft Plugin

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.16.5-brightgreen)
![License](https://img.shields.io/badge/License-MIT-blue)
![Spigot](https://img.shields.io/badge/Spigot-✔-orange)

## 📖 Table of Contents
- [Overview](#-overview)
- [Features](#-features)
- [Commands](#-commands)
- [Permissions](#-permissions)
- [Usage](#-usage)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Dependencies](#-dependencies)
- [Support](#-support)
- [License](#-license)

## 🔍 Overview

Proverka (check) is a plugin for, designed to help admins to perform player cheat verification. It provides a user-friendly interface and a set of commands to efficiently manage the cheat checking process.

## ✨ Features

- 🚀 Quick initiation of player checks
- 🌀 Teleportation of players to a designated check area
- 🧊 Freeze/unfreeze players during checks
- 📦 Inspect player inventories
- ⏱️ Time limit for checks with boss bar display
- 🖥️ GUI for managing checks
- 💬 Discord integration for communication

## 🛠️ Commands

| Command | Description |
|---------|-------------|
| `/proverka <player>` | Start a check on a player |
| `/okproverka` | Put an active check on hold |
| `/ds <discord>` | Allow a player being checked to provide their Discord information |
| `/proverkaaccepted` | Complete a check successfully |
| `/provermenu` | Open the checker menu GUI |

## 🔒 Permissions

The plugin uses the following permission nodes:

- `proverka.use`
- `group.admin1`
- `group.admin2`
- `group.admin3`
- `group.admin4`
- `group.admin5`

Players with any of these permissions can use the plugin's commands.

## 📘 Usage

1. An authorized staff member initiates a check using `/proverka <player>`.
2. The target player is teleported to a designated check area and given 5 minutes to provide their Discord information.
3. The checker can use the GUI menu (accessed via `/provermenu`) to:
   - Teleport the player
   - Freeze/unfreeze the player
   - Check the player's inventory
   - Accept or reject the check
4. The target player can use `/ds <discord>` to provide their Discord information.
5. The checker can complete the check using the GUI or the `/proverkaaccepted` command.

## 💾 Installation

1. Place the Proverka.jar file in your server's `plugins` folder.
2. Restart your server or load the plugin.
3. Configure any necessary permissions for your staff members.

## ⚙️ Configuration

The plugin does not currently have a configuration file :(

## 🔗 Dependencies

This plugin is designed for Spigot servers. Ensure you're running a compatible server version (1.16.5).

## 🆘 Support

For support, please contact the plugin author.

## 📄 License

This project is licensed under the MIT License:

---

Developed with ❤️ for Minecraft server administrators
