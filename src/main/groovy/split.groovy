def hisvak = new XmlParser().parse(args[0])
def hisvak_bron = new XmlParser().parse(args[1])
def hisvak_lid = new XmlParser().parse(args[2])

String normalize(String text) {
    (text?.trim()) ?: '.'
}

hisvak.HISVAK.each {
    def Nrorg_std = it.Nrorg_std.text()
    def bronnen = hisvak_bron.findAll {
        it.Nrorg_std.text() == Nrorg_std
    }
    def leden = hisvak_lid.findAll {
        it.Nrorg_std.text() == Nrorg_std
    }

    bronnen.each {
        new Node(it, 'Bron_concat', normalize(it.NummerOrg.text()) + '_' + normalize(it.Naamorg?.text()) + '_' + normalize(it.Bron?.text()))
    }
    leden.each {
        new Node(it, 'Lid_concat', normalize(it.Nummer_lid.text()) + '_' + normalize(it.Jaar?.text()) + '_' + normalize(it.Aantalleden?.text()) + '_' + normalize(it.Bronlid?.text()))
    }

    new Node(it, 'bronnen', bronnen)
    new Node(it, 'leden', leden)

    def printer = new XmlNodePrinter(new PrintWriter(new File("./import", Nrorg_std + ".xml")))
    printer.preserveWhitespace = true
    printer.print(
            it
    )
}
