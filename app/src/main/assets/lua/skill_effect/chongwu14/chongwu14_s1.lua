--v3
--[[
chongwu14
1
0
penxue
1
{default,gongji1,1,0,0,25,1.000000}
1
{default,0.000000,0.000000,450.000000,220.000000,0,2,0,1,0.000000,0,0,0,0}
0

0

1
{111111,chongwu14,2,0,0.000000,0.000000,25,0}
1
{default,gongji1_texiao,1,0,0,25,1.000000}
1
{default,450.000000,240.000000,450.000000,240.000000,2,2,0,1,0.000000,0,0,0,0}
0

0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"gongji1",1.000000,0,25,1}}, 
pos_sequence      = {{0.000000,0.000000,450.000000,220.000000,0,2,0,1,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"chongwu14",2,0,0.000000,0.000000,{{"gongji1_texiao",1.000000,0,25,1}},{{450.000000,240.000000,450.000000,240.000000,2,2,0,1,0.000000,0,0,0,0}},{},{},0}}, 
bloodNum          = 2,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "penxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
