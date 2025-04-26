# [Clojure](https://clojure.org/) Giant's Shoulders

A starting point for all of my Clojure projects, I hope you will find it useful too. Comes with a fully featured nREPL server, tests, formatter, CI, [debugger](https://github.com/flow-storm/flow-storm-debugger) and [data navigator](https://github.com/djblue/portal).

Requires [mise](https://mise.jdx.dev/) to start various tasks which will also handle the Java and Clojure CLI versions for you automatically. Trust me, you'll love it once you try it.

You can use any [nREPL](https://nrepl.org/nrepl/index.html) based Clojure editor plugins to talk to the REPL but I of course recommend my own, [Conjure](https://github.com/Olical/conjure), if you’re not sure where to start. If you’re really new to Clojure and don’t fancy diving into [Neovim](https://neovim.io/) then I’d probably recommend [Calva](https://calva.io/).

Fork, copy, clone, borrow, read or browse. Do whatever you want with this repository and use it how you see fit. I like to do the following when starting a new project.

```bash
# Notice the `/.` in order to copy the entire directory and it's hidden directories.
cp -r clojure-giants-shoulders/. my-project
cd my-project
rm -rf .git
git init
```

Let's stop talking about bike sheds and go paint some instead. Good luck, have fun!

## LSP

Make sure you have a [LSP](https://microsoft.github.io/language-server-protocol/) client set up for your editor of choice and [clojure-lsp](https://clojure-lsp.io/) installed for the best experience. This will enable extra tooling like linting, in editor formatting, completion and integration with function instrumentation schemas. You can have in editor _type checking_ as you write your Clojure code if you set this up correctly.

## Tasks

We use [mise](https://mise.jdx.dev/) to manage the JVM dependency and tasks we can perform on the project.

### `mise repl`

Starts an interactive REPL with history, highlighting, completion and automatic documentation lookup as you type. Before you start working with the project you should run this in a terminal that you can keep safe in the background somewhere.

You can type code directly into this REPL but I’d recommend using a plugin for an editor of your choosing. It should connect to the REPL automatically when you open a Clojure file in this project thanks to the `.nrepl-port` file.

You may also enable a [portal](https://github.com/djblue/portal) window like so:

```bash
mise repl :portal true
```

This will open a window alongside your REPL (or in your current browser window if you don’t have a Chrome / Chromium installed) that will allow you to visually inspect any values you wrap in `(tap> ...)`. Try it out by evaluating `(tap> (range 10))` in the REPL with the portal window open.

This development REPL also enables the [mount](https://github.com/tolitius/mount) system management and [malli](https://github.com/metosin/malli) function instrumentation.

### `mise test`

Run the test suite with [kaocha](https://github.com/lambdaisland/kaocha). Append `--watch` to execute your tests as you change files.

I highly recommend keeping a `./scripts/kaocha --watch` window open beside your `scripts/repl` window while you work.

### `mise format`

Format all of your code with [cljfmt](https://github.com/weavejester/cljfmt).

### `mise antq`

Find and update dependencies with [antq](https://github.com/liquidz/antq).

```bash
# Check for updates
mise antq

# Actually perform updates
mise antq --upgrade
```

## Unlicenced

Find the full [unlicense](http://unlicense.org/) in the `UNLICENSE` file, but here’s a snippet.

> This is free and unencumbered software released into the public domain.
>
> Anyone is free to copy, modify, publish, use, compile, sell, or distribute this software, either in source code form or as a compiled binary, for any purpose, commercial or non-commercial, and by any means.
