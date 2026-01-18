export function makeCsv(data) {
    let csv =
        "nazivMuzeja,drzava,grad,godinaOsnivanja,tipMuzeja,velicinaKolekcije,posjetitelji,izlozbeniProstor,webStranica,onlineSetnja,nazivEksponata,tipEksponata\n";

    data.forEach(m => {
        m.eksponati.forEach(e => {
            csv +=
                `${formatComma(m.nazivMuzeja)},` +
                `${formatComma(m.drzava)},` +
                `${formatComma(m.grad)},` +
                `${formatComma(m.godinaOsnivanja)},` +
                `${formatComma(m.tipMuzeja)},` +
                `${formatComma(m.velicinaKolekcije)},` +
                `${formatComma(m.posjetitelji)},` +
                `${formatComma(m.izlozbeniProstor)},` +
                `${formatComma(m.webStranica)},` +
                `${formatComma(m.onlineSetnja)},` +
                `${formatComma(e.nazivEksponata)},` +
                `${formatComma(e.tipEksponata)}\n`;
        });
    });

    return csv;
}


function formatComma(value) {
    const str = String(value);
    return str.includes(",") ? `"${str}"` : str;
}