# [Clojure](https://clojure.org/) template

A starting point for all of my Clojure projects, I hope you will find it useful too. Comes with a fully featured nREPL server, tests, formatter, CI and [debugger](https://github.com/flow-storm/flow-storm-debugger).

Requires [mise](https://mise.jdx.dev/) to start various tasks which will also handle the Java and Clojure CLI versions for you automatically. Trust me, you'll love it once you try it.

You can use any [nREPL](https://nrepl.org/nrepl/index.html) based Clojure editor plugins to talk to the REPL but I of course recommend my own, [Conjure](https://github.com/Olical/conjure), if you’re not sure where to start. If you’re really new to Clojure and don’t fancy diving into [Neovim](https://neovim.io/) then I’d probably recommend [Calva](https://calva.io/).

Fork, copy, clone, borrow, read or browse. Do whatever you want with this repository and use it how you see fit. I like to do the following when starting a new project.

> This repository is a GitHub template, so you can create a new repository based on it if you'd like! Or copy the files as shown below.

```bash
# Notice the `/.` in order to copy the entire directory and it's hidden directories.
cp -r clojure-template/. my-project
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

Starts an nREPL server. Before you start working with the project you should run this in a terminal that you can keep safe in the background somewhere.

Evaluate the keyword `:dbg` in order to open the [flowstorm](https://github.com/flow-storm/flow-storm-debugger) debugger window. How to use flowstorm is outside the scope of this README, but it's worth learning!

### `mise test`

Run the test suite with [kaocha](https://github.com/lambdaisland/kaocha). Append `--watch` to execute your tests as you change files.

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

### `mise clean / build / publish`

Handles building and publishing JARs to Clojars.

### `mise typecheck`

Type checking is provided by [Typed Clojure](https://github.com/typedclojure/typedclojure), my own [typedclojure-lsp](https://github.com/Olical/typedclojure-lsp) is pre-configured in this repository (see `.typedclojure-lsp/start`), you just need to add the LSP server configuration to your text editor.

## GitHub actions

- Test: Runs the test suite through Kaocha.
- Typecheck: Checks any marked namespaces with Typed Clojure.

## Unlicenced

Find the full [unlicense](http://unlicense.org/) in the `UNLICENSE` file, but here’s a snippet.

> This is free and unencumbered software released into the public domain.
>
> Anyone is free to copy, modify, publish, use, compile, sell, or distribute this software, either in source code form or as a compiled binary, for any purpose, commercial or non-commercial, and by any means.
