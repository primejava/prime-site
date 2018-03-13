
$('#builder-basic').queryBuilder({
    plugins: ['bt-tooltip-errors'],
    conditions:['AND','OR'],
    filters: [{
        id: 'username',
        label: '用户名',
        type: 'string',
        operators: ['equal']
    },
    {
        id: 'status',
        label: '状态',
        type: 'integer',
        input: 'select',
        values: {
            1: '可用',
            0: '禁用'
        },
        operators: ['equal']
    }]
});