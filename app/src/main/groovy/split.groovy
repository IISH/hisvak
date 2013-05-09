def hisvak = new XmlParser().parse(args[0])
def hisvak_bron = new XmlParser().parse(args[1])
def hisvak_lid = new XmlParser().parse(args[2])

hisvak.HISVAK.each {
    def Nrorg_std = it.Nrorg_std.text()
    def bronnen = hisvak_bron.findAll {
        it.Nrorg_std.text() == Nrorg_std
    }
    def leden = hisvak_lid.findAll {
        it.Nrorg_std.text() == Nrorg_std
    }

    new Node(it, 'bronnen', bronnen)
    new Node(it, 'leden', leden)

    def printer = new XmlNodePrinter(new PrintWriter(new File("./normalized", Nrorg_std + ".xml")))
    printer.preserveWhitespace = true
    printer.print(
            it
    )
}
