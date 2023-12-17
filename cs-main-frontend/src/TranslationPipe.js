const map = new Map();
map.set('en', {
    routeFrom: 'Route from',
    routeTo: 'Route to',
    capacity: 'Capacity',
    numberOfVisitedPorts: 'Number of visited ports',
    startDate: 'Start date',
    endDate: 'End date',
    orderedSeats: 'Ordered seats',
    cruiseShip_routeTo: 'Cruise Ship Route to',
    cruiseShip_routeFrom: 'Cruise Ship Route from',
});

export function TranslationPipe(key) {
    let keyWithoutDot = key.replaceAll('.', '_');
    return map.get('en')[keyWithoutDot];
}